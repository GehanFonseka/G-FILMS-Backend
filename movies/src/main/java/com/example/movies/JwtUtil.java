package com.example.movies;

import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Define the secret key for HMAC SHA256
    private static final String SECRET_KEY = "Your256BitSecretYour256BitSecretYour256BitSecret"; // Use a 256-bit secret

    // Generate JWT token
    public String generateToken(String username) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTime = currentTimeMillis + 1000 * 60 * 60; // 1-hour expiration

        // Encode header and payload in Base64
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payload = "{\"sub\":\"" + username + "\",\"iat\":" + currentTimeMillis + ",\"exp\":" + expirationTime + "}";

        String encodedHeader = Base64.getUrlEncoder().encodeToString(header.getBytes());
        String encodedPayload = Base64.getUrlEncoder().encodeToString(payload.getBytes());

        // Create the signature
        String signature = generateSignature(encodedHeader, encodedPayload);

        // Return the final JWT token
        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid JWT token");
        }

        String decodedPayload = new String(Base64.getUrlDecoder().decode(parts[1]));
        // Extract username from the decoded payload
        return decodedPayload.split(",")[0].split(":")[1].replace("\"", "");
    }

    // Validate the JWT token
    public boolean validateToken(String token, String username) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }

        String encodedHeader = parts[0];
        String encodedPayload = parts[1];
        String receivedSignature = parts[2];

        // Regenerate the signature based on the header and payload
        String regeneratedSignature = generateSignature(encodedHeader, encodedPayload);

        // Compare the signatures
        return receivedSignature.equals(regeneratedSignature) && username.equals(extractUsername(token));
    }

    // Generate the HMAC SHA256 signature
    private String generateSignature(String encodedHeader, String encodedPayload) {
        try {
            String data = encodedHeader + "." + encodedPayload;

            // Create HMAC SHA256 instance
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);

            // Generate the HMAC signature
            byte[] rawHmac = mac.doFinal(data.getBytes());

            // Return the signature in Base64 URL encoding
            return Base64.getUrlEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT signature", e);
        }
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid JWT token");
        }

        String decodedPayload = new String(Base64.getUrlDecoder().decode(parts[1]));
        // Extract the expiration time (exp) from the payload
        String expString = decodedPayload.split(",")[2].split(":")[1];
        long expTime = Long.parseLong(expString);

        // Check if the token has expired
        return System.currentTimeMillis() > expTime;
    }
}