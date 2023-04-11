package ru.mirea.egorovakv.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyCryptoLoader extends AsyncTaskLoader<String> {
    private	byte[] codedMessage;
    private byte[] key;
    private	String decodedMessage;
    public	static	final	String	ARG_WORD	=	"content";
    public	MyCryptoLoader(@NonNull Context context, Bundle args)	{
        super(context);
        if	(args	!=	null) {
            codedMessage = args.getByteArray(ARG_WORD);
            key = args.getByteArray("key");
        }

    }
    @Override
    protected	void	onStartLoading()	{
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public	String	loadInBackground()	{
//        SystemClock.sleep(3000);
        SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");
        decodedMessage = decryptMsg(codedMessage, originalKey);
        return	decodedMessage;
    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
