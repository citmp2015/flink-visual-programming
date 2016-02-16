# API

## REST

#### PUT /graph
Request Body:
```json
{"processes":{"a2243cd6bd3704386bc354352f09473fc":{"component":"textdatasource","data":{"filePath":"hdfs://examples/wordcount.txt","output_type":"String"}},"a46e800352ca249879058a3461d184f68":{"component":"groupBy","data":{"tupleIndex":0}},"a86f782fb5e5c43e495116a706960a4ee":{"component":"flatmap","data":{"javaSourceCode":"public class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {\n\n  @Override\n  public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {\n    \n    // normalize and split the line\n    String[] tokens = value.toLowerCase().split(\"\\\\W+\");\n\n    // emit the pairs\n    for (String token : tokens) {\n      if (token.length() > 0) {\n        out.collect(new Tuple2<String, Integer>(token, 1));\n      }\n    }\n    \n  }\n}","functionName":"LineSplitter","input_type":"String","output_type":"Tuple2<String, Integer>"}},"ae1a677f6f1534058938f7f5a8c7bda3c":{"component":"sum","data":{"tupleIndex":1}},"a8fdf24cd659846c1b00e07110a10d773":{"component":"fastCreate: CSV Datasink","data":{}}},"connections":[{"id":"a4f223b21a2c54762aa7e20b12ee6730e","src":"a2243cd6bd3704386bc354352f09473fc","tgt":"a86f782fb5e5c43e495116a706960a4ee"},{"id":"a8ffe5a1d007a42c88a7b2cf14c4a5c5d","src":"a86f782fb5e5c43e495116a706960a4ee","tgt":"a46e800352ca249879058a3461d184f68"},{"id":"a880b48ce327049d0b4d719d85bd6b875","src":"a46e800352ca249879058a3461d184f68","tgt":"ae1a677f6f1534058938f7f5a8c7bda3c"},{"id":"af4cdf76262d746a6a30210eba7bb4d27","src":"ae1a677f6f1534058938f7f5a8c7bda3c","tgt":"a8fdf24cd659846c1b00e07110a10d773"}]}
```

Response Body:
```js
{
  "uuid": ":UUID", //String
  "status": "submitted" //String: submitted, generating, building, builded, error
  "log": null //String
}
```

#### GET /graph/:UUID
Response Body:
```js
{
  "uuid": ":UUID", //String
  "status": "submitted" //String: submitted, generating, building, builded, error
  "log": null //String
}
```

#### GET /graph/:UUID/source.zip
Response: ZIP file

#### GET /graph/:UUID/job.jar
Response: JAR file

#### DEPLOY /graph/:UUID
Response Body:
```js
{
  "uuid": ":UUID", //String
  "status": "submitted" //String: submitted, generating, building, builded, error
  "log": null //String
}
```


## WebSocket Server Events

* `graph:UUID:generationStarted`
* `graph:UUID:generationSucceeded`
* `graph:UUID:generationError`
* `graph:UUID:mvnBuildStarted`
* `graph:UUID:mvnBuildOutput OUTPUT`
* `graph:UUID:mvnBuildError`
* `graph:UUID:mvnBuildSucceeded`
