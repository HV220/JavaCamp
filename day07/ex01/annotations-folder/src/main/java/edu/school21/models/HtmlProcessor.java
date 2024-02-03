package edu.school21.models;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import com.google.auto.service.AutoService;

@SupportedAnnotationTypes({ "edu.school21.models.HtmlForm", "edu.school21.models.HtmlInput" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm formAnnotation = element.getAnnotation(HtmlForm.class);
            String fileName = formAnnotation.fileName();
            String action = formAnnotation.action();
            String method = formAnnotation.method();

            StringBuilder builder = new StringBuilder();
            builder.append("<form action=\"").append(action).append("\" method=\"").append(method).append("\">\n");

            for (Element enclosed : element.getEnclosedElements()) {
                HtmlInput inputAnnotation = enclosed.getAnnotation(HtmlInput.class);
                if (inputAnnotation != null) {
                    builder.append("  <input")
                            .append(" type=\"").append(inputAnnotation.type()).append("\"")
                            .append(" name=\"").append(inputAnnotation.name()).append("\"")
                            .append(" placeholder=\"").append(inputAnnotation.placeholder()).append("\">\n");
                }
            }

            builder.append("  <input type=\"submit\" value=\"Send\">\n");
            builder.append("</form>");
            try {
                FileObject file = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", fileName);
                try (Writer writer = file.openWriter()) {
                    writer.write(builder.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}