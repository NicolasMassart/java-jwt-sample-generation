package jwt;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenerateJWT {

  private enum Algo {
    RS256, ES256
  }

  public static void main(String[] args) throws Exception {
    Vertx vertx = Vertx.vertx();

    JsonObject jsonTokenPayload = new JsonObject()
        .put("permissions", "*:*")
        .put("privacyPublicKey", "2UKH3VJThkOoKskrLFpwoxCnnRARyobV1bEdgseFHTs=")
        .put("exp", "1600899999002");

    String privateRSAKey = Files.readString(Path.of("RSA_private_key.pem"));
    String publicRSAKey = Files.readString(Path.of("RSA_public.pem"));
    System.out.printf("\nRSA JWT: %s\n",generate(vertx, Algo.RS256, privateRSAKey, publicRSAKey, jsonTokenPayload));

    String privateECDSAKey = Files.readString(Path.of("ECDSA_private_key.pem"));
    String publicECDSAKey = Files.readString(Path.of("ECDSA_public.pem"));
    System.out.printf("\nECDSA JWT: %s\n",generate(vertx, Algo.ES256, privateECDSAKey, publicECDSAKey, jsonTokenPayload));
  }

  private static String generate(Vertx vertx, Algo algo, String privateKey,
      String publicKey, JsonObject payload) throws IOException {
    JWTAuth provider = JWTAuth.create(vertx, new JWTAuthOptions()
        .addPubSecKey(new PubSecKeyOptions()
            .setAlgorithm(algo.name())
            .setBuffer(publicKey))
        .addPubSecKey(new PubSecKeyOptions()
            .setAlgorithm(algo.name())
            .setBuffer(privateKey)));

    return provider.generateToken(
        payload,
        new JWTOptions().setAlgorithm(algo.name()));
  }
}
