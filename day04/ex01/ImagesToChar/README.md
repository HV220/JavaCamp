ImagesToChar Application

Compile app
javac -d target src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ImageToCharConverter.java && cp -r src/resources/ target/.

Create JAR archive
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

Run app
java -jar target/images-to-chars-printer.jar 1 0 target/resources/image.bmp