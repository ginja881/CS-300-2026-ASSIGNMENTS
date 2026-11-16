// PROVIDED FILE

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

/**
 * Used to generate IDs for commits by hashing the message with the timestamp.
 * Ensures no collisions occur by tracking used hashes.
 * You do not need to understand the inner workings of this class.
 */
public class HashGenerator {
  private static final Set<String> usedHashes = new HashSet<>();

  /**
   * Generates a 6-character hex short hash for a commit ID.
   * @param commitMessage the commit message, to help ensure uniqueness
   * @return a String, the generated unique hash
   */
  public static String generateShortHash(String commitMessage) {
    String hash;
    do {
      try {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        String input = commitMessage + System.nanoTime();
        byte[] encodedHash = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
          String hex = Integer.toHexString(0xff & encodedHash[i]);
          if (hex.length() == 1) {
            hexString.append('0');
          }
          hexString.append(hex);
        }
        hash = hexString.toString();

      } catch (Exception e) {
        hash = Integer.toHexString(Math.abs((commitMessage + System.nanoTime()).hashCode()));
        while (hash.length() < 6) hash += "0";
        hash = hash.substring(0, 6);
      }
    } while (usedHashes.contains(hash));

    usedHashes.add(hash);
    return hash;
  }
}

