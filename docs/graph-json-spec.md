#Graph JSON format specification

The root of the graph is a [graph object](#graph_object). The graph consists of the following object types:

##Graph object

The basis for this format is the graph object. It __must__ provide the following properties:

- `processes`: An object mapping process identifiers to [process objects](#process_object).
- `connections`: An array of [connection objects](#connection_object).
- `components`: An object mapping component class names to [component objects](#component_object).

###Example

```json
{
  "processes": {
    "process1": { [PROCESS OBJECT] },
    "process2": …
  },
  "connections": [{ CONNECTION OBJECT }, …],
  "components": {
    "componentClass1": { [COMPONENT OBJECT] },
    "componentClass2": …
  }
}
```

##Process object

##Connection objects

##Component object

_Note: This spec is derived from the NoFlo JSON graph format._
