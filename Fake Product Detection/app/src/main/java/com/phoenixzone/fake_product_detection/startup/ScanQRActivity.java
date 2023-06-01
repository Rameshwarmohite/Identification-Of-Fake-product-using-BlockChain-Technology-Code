package com.phoenixzone.fake_product_detection.startup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.connectivity.JSONParser;
import com.phoenixzone.fake_product_detection.connectivity.ServerUtility;
import com.phoenixzone.fake_product_detection.product.ItemDetailsActivity;
import com.phoenixzone.fake_product_detection.utility.ProductInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanQRActivity extends AppCompatActivity {
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private static final int REQUEST_EXTERNAL_PERMISSION = 200;

    //variables to open scanner
    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    JSONParser jsonParser = new JSONParser();
    public static final String STRING_PRODUCT_NAME = "ProductName";
    public static final String STRING_PRODUCT_PRICE = "ProductPrice";
    public static final String STRING_IMAGE_URI = "ImageUri";
    public static final String STRING_IMAGE_POSITION = "ImagePosition";
    String intentData = "";

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qractivity);
        ImageView imgScan = findViewById(R.id.imageScan);
        imgScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanner();
            }
        });
        flag = false;

        if (ActivityCompat.checkSelfPermission(ScanQRActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(ScanQRActivity.this, new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_PERMISSION);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void openScanner() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_scanner, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        txtBarcodeValue = dialogView.findViewById(R.id.txtBarcodeValue);
        surfaceView = dialogView.findViewById(R.id.surfaceView);
        Button btnAction = dialogView.findViewById(R.id.btnAction);
//        btnAction.setText("Get ISN");
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentData.length() > 0) {
                    Toast.makeText(ScanQRActivity.this, intentData, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ScanQRActivity.this, "ISN Not Detected", Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();
            }
        });


        initialiseDetectorsAndSources(alertDialog);

    }


    //initialise the camera for scanning

    private void initialiseDetectorsAndSources(final AlertDialog alertDialog) {

        Toast.makeText(this, "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(500, 500)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanQRActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanQRActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(ScanQRActivity.this, "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {

                    intentData = barcodes.valueAt(0).displayValue;
                    txtBarcodeValue.post(new Runnable() {

                        @Override
                        public void run() {

                            intentData = barcodes.valueAt(0).displayValue;
                            txtBarcodeValue.setText(intentData);
                            alertDialog.dismiss();

                            if (!flag) {
                                new LoadProductInfo(intentData).execute();
                                flag = true;
                            }


                        }
                    });

                }
            }
        });
    }

    //load product details

    public class LoadProductInfo extends AsyncTask<String, String, String> {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        boolean isLoaded = false;
        String productId;
        String url = ServerUtility.url_get_product_by_id_info();


        private String product_id;
        private String category_fk;
        private String product_name;
        private String product_desc;
        private String product_price;
        private String product_photo;
        private double delivery_fee = 15.0;

        public LoadProductInfo(String productId) {
            this.productId = productId;
        }

        @Override
        protected void onPreExecute() {
            params.add(new BasicNameValuePair("productId", productId));
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            JSONObject jsonObject = jsonParser.makeHttpRequest(url, "GET", params);

            try {

                JSONArray jsonArray = jsonObject.getJSONArray("ProductInfo");
                if (jsonArray.length() == 0) {
                    Intent intent = new Intent(ScanQRActivity.this, NoProductFoundActivity.class);
                    startActivity(intent);
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        product_id = object.getString("product_pk_id");
                        category_fk = object.getString("product_category_fk");
                        product_name = object.getString("product_name");
                        product_desc = object.getString("product_desc");
                        product_price = object.getString("product_price");
                        delivery_fee = object.getDouble("d_charge");
                        product_photo = ServerUtility.Server_URL + object.getString("product_photo");
                        ProductInfo productInfo = new ProductInfo(product_id, category_fk, product_name, product_desc, product_price, product_photo);
                        ItemDetailsActivity.selectedProduct = productInfo;
                        Intent intent = new Intent(ScanQRActivity.this, ItemDetailsActivity.class);
                        intent.putExtra(STRING_IMAGE_URI, productInfo.getProduct_photo());
                        intent.putExtra(STRING_IMAGE_POSITION, 0);
                        intent.putExtra(STRING_PRODUCT_NAME, productInfo.getProduct_name());
                        intent.putExtra(STRING_PRODUCT_PRICE, productInfo.getProduct_price());
                        startActivity(intent);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}