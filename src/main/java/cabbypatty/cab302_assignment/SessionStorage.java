package cabbypatty.cab302_assignment;

import java.util.Base64;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for storing and retrieving encrypted session tokens.
 * Uses Java Preferences API for storing the encrypted token and AES for encryption.
 */
public class SessionStorage {
    private static final String PREF_NAME = "AppPrefs";
    private static final String KEY_PREF = "EncryptionKey";

    /**
     * Saves the session token in encrypted form.
     *
     * @param token The session token to be saved.
     * @throws Exception If an error occurs during encryption or saving the token.
     */
    public static void saveToken(String token) throws Exception {
        SecretKey key = getKey(); // Retrieve or generate key
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedToken = cipher.doFinal(token.getBytes());

        Preferences prefs = Preferences.userRoot().node(PREF_NAME);
        prefs.putByteArray("session_id", encryptedToken);
        prefs.flush();
    }

    /**
     * Loads the session token in decrypted form.
     *
     * @return The decrypted session token, or null if no token is found.
     * @throws Exception If an error occurs during decryption or loading the token.
     */
    public static String loadToken() throws Exception {
        Preferences prefs = Preferences.userRoot().node(PREF_NAME);
        byte[] encryptedToken = prefs.getByteArray("session_id", null);
        if (encryptedToken == null) {
            return null; // or throw an appropriate exception
        }

        SecretKey key = getKey(); // Retrieve the key
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedTokenBytes = cipher.doFinal(encryptedToken);

        return new String(decryptedTokenBytes);
    }

    /**
     * Retrieves the encryption key from preferences, or generates a new key if none is found.
     *
     * @return The AES SecretKey for encryption and decryption.
     * @throws Exception If an error occurs during key generation or retrieval.
     */
    private static SecretKey getKey() throws Exception {
        Preferences prefs = Preferences.userRoot().node(PREF_NAME);
        String keyEncoded = prefs.get(KEY_PREF, null);
        if (keyEncoded == null) {
            // Generate a new key
            SecretKey newKey = KeyGenerator.getInstance("AES").generateKey();
            String base64Key = Base64.getEncoder().encodeToString(newKey.getEncoded());
            prefs.put(KEY_PREF, base64Key);
            prefs.flush();
            return newKey;
        } else {
            // Decode the base64 encoded key
            byte[] decodedKey = Base64.getDecoder().decode(keyEncoded);
            return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        }
    }

    /**
     * Clears the saved session token from preferences.
     *
     * @throws BackingStoreException If an error occurs while clearing the preferences.
     */
    public static void clearToken() throws BackingStoreException {
        Preferences prefs = Preferences.userRoot().node(PREF_NAME);
        prefs.remove("session_id");
        prefs.flush();
    }
}
