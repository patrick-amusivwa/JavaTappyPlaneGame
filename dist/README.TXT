========================
BUILD OUTPUT DESCRIPTION
========================

At the point when you fabricate a Java application project that has a fundamental class, the IDE
naturally duplicates the entirety of the Container
documents on the tasks classpath to your ventures dist/lib organizer. The IDE
additionally adds every one of the Container records to the Class-Way component in the application
Container documents manifest record (MANIFEST.MF).

To run the undertaking from the order line, go to the dist envelope and
type the accompanying:

java - container "TappyPlaneGame.jar"

To disperse this venture, speed up the dist envelope (counting the lib organizer)
also, appropriate the Compress document.

Notes:

* On the off chance that two Container records on the undertaking classpath have a similar name, just the first
Container record is duplicated to the lib organizer.
* Just Container records are duplicated to the lib organizer.
If the classpath contains different sorts of records or envelopes, these documents (envelopes)
are not replicated.
* In the event that a library on the undertakings classpath likewise has a Class-Way component
determined in the manifest,the content of the Class-Way component must be on
the undertakings runtime way.
* To set a principal class in a standard Java project, right-click the undertaking hub
in the Undertakings window and pick Properties. Then, at that point, click Run and enter the
class name in the Primary Class field. On the other hand, you can physically type the
class name in the manifest Primary Class component.
