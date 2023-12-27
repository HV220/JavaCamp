ImagesToChar Application

Compile app
javac -d target -cp ".:lib/*" src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ImageToCharConverter.java && cp -r src/resources/ target/.

Create JAR archive
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

Run app
java -jar target/images-to-chars-printer.jar --white GREEN --black RED target/resources/image.bmp