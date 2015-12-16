#Graph JSON format specification

The root of the graph is a [graph object](#graph-object). The graph consists of the following object types:

##Graph object

The basis for this format is the graph object. It __must__ provide the following properties:

- `processes`: An object mapping [process identifiers](#process-identifier) to [process objects](#process-object).
- `connections`: An array of [connection objects](#connection-object).
- `components`: An object mapping [component class names](#component-class-name) to [component objects](#component-object).

###Example

```json
{
  "processes": {
    "process1": { [PROCESS_OBJECT] },
    "process2": …
  },
  "connections": [{ CONNECTION_OBJECT }, …],
  "components": {
    "componentClass1": { [COMPONENT_OBJECT] },
    "componentClass2": …
  }
}
```

##Process identifier

A process identifier is a unique string identifying a process. It can be arbitrarily picked on the initialization of the component.

##Process object

The process object describes an instance of a component inside the graph. It __must__ have the following properties:

- `component`: The [class name](#component-class-name) of the component the process is an instance of.

It _may_ further have the following properties:

- `label`: Displayed name of the component. _Might be useful for compilation information._
- `x`, `y`: Integers for the `x` and `y` position of the process inside the interface. _Might be useful for receiving a graph from the server._

##Connection object



##Component class name

A component class name is used to identify components. The class name must correspond to a class file that's available to the compiler.

##Component object

_Note: This spec is derived from the NoFlo JSON graph format._
