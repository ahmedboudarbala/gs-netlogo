`gs` is a [NetLogo](http://ccl.northwestern.edu/netlogo/index.shtml) extension connecting NetLogo to other applications supporting the [NetStream](https://github.com/graphstream/gs-netstream) protocol, such as [GraphStream](http://graphstream-project.org/)-based applications. NetLogo agents can use its primitives to send graph events. By receiving and processing these events, the external application can maintain and analyze a dynamic graph view of a NetLogo simulation. The nodes of this graph correspond to the turtles and its edges are the links between them. The agents can also receive graph events from external applications and use them to take their decisions.

Check the [wiki](https://github.com/graphstream/gs-netlogo/wiki) for more information about this extension.

## Building

Use the netlogo-6.3.0.jar.url environment variable to tell sbt which NetLogo.jar to compile against (defaults to NetLogo 6.3). For example:

    sbt -Dnetlogo-6.3.0.jar.url=file://"/home/.../Desktop/NetLogo 6.3.0/lib/app/netlogo-6.3.0.jar" package

If compilation succeeds, `gs.jar` will be created.

