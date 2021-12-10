# Example JWT generation

## Generate keys

### RSA

```bash
openssl genrsa -out gen-keys/RSA_private.pem 2048
openssl pkcs8 -topk8 -inform PEM -in gen-keys/RSA_private.pem -out gen-keys/RSA_private_key.pem -nocrypt
openssl rsa -in gen-keys/RSA_private.pem -outform PEM -pubout -out gen-keys/RSA_public.pem
```

### ECDSA

```bash
openssl ecparam -name secp256r1 -genkey -out gen-keys/ECDSA_private.pem
openssl pkcs8 -topk8 -nocrypt -in gen-keys/ECDSA_private.pem -out gen-keys/ECDSA_private_key.pem
openssl ec -in gen-keys/ECDSA_private.pem -pubout -out gen-keys/ECDSA_public.pem
```

## Generate JWT

To generate JWT, with RSA and ECDSA and the following payload:

```json
{
"permissions": ["*:*"],
"privacyPublicKey": "2UKH3VJThkOoKskrLFpwoxCnnRARyobV1bEdgseFHTs=",
"exp": 1600899999002
}
```

run:

```bash
./gradlew run
```

### Expected output

Output should look like the following:

```text
RSA JWT: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJwZXJtaXNzaW9ucyI6Iio6KiIsInByaXZhY3lQdWJsaWNLZXkiOiIyVUtIM1ZKVGhrT29Lc2tyTEZwd294Q25uUkFSeW9iVjFiRWRnc2VGSFRzPSIsImV4cCI6IjE2MDA4OTk5OTkwMDIiLCJpYXQiOjE2MzkxNTc2Mjd9.FGf-FmfDQlIPCRDGmNnsHZWlwrUr69d7AIDqQrIrUrSJLiwGpR3NCUhVHIDMpQvDHQYf-sFMZTYvZGrvztYRuBKWMbTfIZKN74onzNJbFIPBVQuUX2HMXmI4VQ3UFB11LShiUJHKLna13qdbqfbgJIO3HetxJhJQxTiwtixfHwyPXl-Nx8HbQy_AWH58lLAUeaoLzN7QIA9kborthBpvfK9C7Sv1lXT1cdCDC4oRKBoiMg2RWFZtGtxFsnWyloangwbhCB6Bc_elqY5nd9WkF4ix95xsP_HgBcouy1sDw6jxn5_LveX53H8owczVWP6S1e6hv6hq2fs6YkSntKMK2g

ECDSA JWT: eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJwZXJtaXNzaW9ucyI6Iio6KiIsInByaXZhY3lQdWJsaWNLZXkiOiIyVUtIM1ZKVGhrT29Lc2tyTEZwd294Q25uUkFSeW9iVjFiRWRnc2VGSFRzPSIsImV4cCI6IjE2MDA4OTk5OTkwMDIiLCJpYXQiOjE2MzkxNTc2Mjd9.63r6qRzzgs0Ky1-MBB7YHVf924ZdYk9Kley4vPaH1jQzpYx1J_7BrZOX8eGTCHgB-QlzBA_6PTv5JkbSARPHhA
```
