To compile the code, navigate to the project's root directory and run:

javac -d target src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ImageToCharConverter.java

To run the application, use the following command:

java -cp target edu.school21.printer.app.Main <white_character> <black_character> <path_to_image>

Replace <white_character>, <black_character>, and <path_to_image> with your desired characters and the path to the BMP image respectively.