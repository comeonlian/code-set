package com.leolian.code.fragment.book.jvm.chapter10.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/************** 插入式注解处理器 *******************/
//可以用"*"表示支持所有的Annotation
@SupportedAnnotationTypes("*")
//只支持JDK1.8的Java代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

	private NameChecker nameChecker;

	@Override
	public void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotation, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			for (Element element : roundEnv.getRootElements())
				nameChecker.checkNames(element);
		}
		return false;
	}

}