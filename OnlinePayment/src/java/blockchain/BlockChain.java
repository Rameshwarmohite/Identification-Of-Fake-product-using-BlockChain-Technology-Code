/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import pack.DBConnection;

/**
 *
 * @author Dinesh
 */
public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public static int difficulty = 3;
    public static float minimumTransaction = 0.1f;
    public static Wallet walletA;
    public static Wallet walletB;
    public static Transaction genesisTransaction;

    public static void main(String[] args) {

        float amountTransfer = 0;
        float amountA = 0;
        String sender_id = args[2];//sender id
        String receiver_id = args[3];//receiver id
        float balance = Float.parseFloat(args[4]);

        amountTransfer = Float.parseFloat(args[1]);

        amountA = Float.parseFloat(args[0]) + balance;
        balance = amountTransfer + Float.parseFloat(args[0]);

        //add our blocks to the blockchain ArrayList:
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider

        //Create wallets:
        walletA = new Wallet();
        walletB = new Wallet();
        Wallet coinbase = new Wallet();

        //create genesis transaction, which sends x amount to walletA: 
        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, amountA, null);
        genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
        genesisTransaction.transactionId = "0"; //manually set the transaction id
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.

        System.out.println("Creating and Mining Genesis block... ");
        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        //testing
        Block block1 = new Block(genesis.hash);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("\nWalletA is Attempting to send funds  to WalletB...");
        boolean flag = false;

        flag = block1.addTransaction(walletA.sendFunds(walletB.publicKey, balance));

        addBlock(block1);

        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());

        if (isChainValid() && flag) {
            try {
                DBConnection db = new DBConnection();
                String prev_hash = "0";
                String sql = "SELECT * FROM items ORDER BY id DESC LIMIT 1";
                ResultSet rs = db.Select(sql);
                if (rs.next()) {
                    //get last prevhash from table
                    prev_hash = rs.getString("current_hash");
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String timeStamp = simpleDateFormat.format(date);
                sql = "INSERT INTO tbl_blockchain(sender_id,receiver_id,amount,transaction_date,prev_hash,current_hash,status) "
                        + "VALUES('" + sender_id + "','" + receiver_id + "','" + amountTransfer + "','" + timeStamp + "','" + prev_hash + "','" + block1.hash + "','Success')";
                int row_affected = db.Update(sql);
                if (row_affected > 0) {

                    sql = "UPDATE tbl_user_account SET tamount=" + walletA.getBalance() + " WHERE email='" + sender_id + "'";
                    row_affected = db.Update(sql);
                    sql = "UPDATE tbl_user_account SET tamount=" + walletB.getBalance() + " WHERE email='" + receiver_id + "'";
                    row_affected = db.Update(sql);

                }
            } catch (Exception e) {

            }
        } else {
            System.out.println("There is an error");
        }

    }

    public static List isTransactionDone(String args[]) {
        List list = new ArrayList();
        float amountTransfer = 1;
        float amountA = 10;
        String sender_id = args[2];//sender id
        String receiver_id = args[3];//receiver id
        float balance = 10;
        //add our blocks to the blockchain ArrayList:
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider

        //Create wallets:
        walletA = new Wallet();
        walletB = new Wallet();
        UTXOs = new HashMap<String, TransactionOutput>();
        blockchain = new ArrayList<Block>();
        Wallet coinbase = new Wallet();

        //create genesis transaction, which sends 100 NoobCoin to walletA: 
        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, amountA, null);
        genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
        genesisTransaction.transactionId = "0"; //manually set the transaction id
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.

        System.out.println("Creating and Mining Genesis block... ");
        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        //testing
        Block block1 = new Block(genesis.hash);
        boolean flag = false;

        flag = block1.addTransaction(walletA.sendFunds(walletB.publicKey, balance));

        addBlock(block1);

        if (isChainValid() && flag) {
            try {
                DBConnection db = new DBConnection();
                String prev_hash = "0";
                String sql = "SELECT * FROM items ORDER BY id DESC LIMIT 1";
                ResultSet rs = db.Select(sql);
                if (rs.next()) {
                    //get last prevhash from table
                    prev_hash = rs.getString("current_hash");
                }
                list.add(true);
                list.add(prev_hash);
                list.add(block1.hash);
            } catch (Exception e) {

            }
        } else {

            list.add(false);
            System.out.println("There is an error");

        }
        return list;
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String, TransactionOutput> tempUTXOs = new HashMap<String, TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("#This block hasn't been mined");
                return false;
            }

            //loop thru blockchains transactions:
            TransactionOutput tempOutput;
            for (int t = 0; t < currentBlock.transactions.size(); t++) {
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if (!currentTransaction.verifiySignature()) {
                    System.out.println("#Signature on Transaction(" + t + ") is Invalid");
                    return false;
                }
                if (currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
                    System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
                    return false;
                }

                for (TransactionInput input : currentTransaction.inputs) {
                    tempOutput = tempUTXOs.get(input.transactionOutputId);

                    if (tempOutput == null) {
                        System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
                        return false;
                    }

                    if (input.UTXO.value != tempOutput.value) {
                        System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
                        return false;
                    }

                    tempUTXOs.remove(input.transactionOutputId);
                }

                for (TransactionOutput output : currentTransaction.outputs) {
                    tempUTXOs.put(output.id, output);
                }

                if (currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
                    System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
                    return false;
                }
                if (currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
                    System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
                    return false;
                }

            }

        }
        System.out.println("Blockchain is valid");
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
