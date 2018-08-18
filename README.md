credit-card-util
================

## BIN Lookup

 - https://www.neutrinoapi.com/api/bin-lookup/

### Configuration

create file test/resource/`config.properties`

```
user-id = <YOUR-USER-ID>
api-key = <YOUR-API-KEY>
```

Java Example (see: test/src/util/`BinLookupTest.java`)

```java
    AppConfig config = new AppConfig("config.properties");
    BinLookup bin = new BinLookup(config.getString("user-id"), config.getString("api-key"));
    bin.check("455205", "171.5.248.213");
```

### Run

```
mvn clean test
```

### Result

```json
{
  "valid" : true,
  "country" : "THAILAND",
  "country-code" : "TH",
  "card-type" : "CREDIT",
  "card-brand" : "VISA",
  "card-category" : "PLATINUM",
  "issuer-phone" : "0-2296-3000",
  "currency-code" : "THB",
  "country-code3" : "THA",
  "issuer" : "BANK OF AYUDHYA PUBLIC CO., LTD. (KRUNGSRI)",
  "issuer-website" : "http://www.bankthailand.info/bankayudhya.htm"
}
```