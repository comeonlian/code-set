package com.leolian.code.fragment.book.jvm.chapter10.annotation;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;

/**
 * 程序名称规范的编译器插件，如果程序命名不合规范，将会输出一个编译器的WARNING信息
 * @description: 
 * @author lianliang
 * @date 2018年9月19日 上午11:33:17
 */
public class NameChecker {
	
	private final Messager message;
	
	NameCheckScanner nameCheckerScanner = new NameCheckScanner();
	
	NameChecker (ProcessingEnvironment processingEnv) {
		this.message = processingEnv.getMessager();
	}
	
	/**
	对Java程序命名进行检查，根据<Java语言规范>的要求，Java程序命名应当符合下列各式
	类或接口：符合驼式命名法、首字母大写
	方法：符合驼式命名法，首字母小写
	类、实例变量：符合驼式命名法，首字母小写
	常量：要求全部大写
	*/
	public void checkNames(Element element) {
		nameCheckerScanner.scan(element);
	}
	
	/**
	名称检查器实现类，继承了JDK1.8中新提供的ElementScanner8
	将会以Visitor模式访问抽象语法树中的元素
	*/
	private class NameCheckScanner extends ElementScanner8<Void, Void> {
		
		// 此方法用于检查Java类
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCases(e, true);
			super.visitType(e, p);
			return null;
		}
		
		// 检查方法命名是否合法
		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if(e.getKind() == ElementKind.METHOD) {
				Name name = e.getSimpleName();
				if(name.contentEquals(e.getEnclosingElement().getSimpleName()))
					message.printMessage(Diagnostic.Kind.WARNING, "一个普通方法" + name + "不应当与类名重复，避免与构造函数产生混淆", e);
				checkCamelCases(e, false);
			}
			super.visitExecutable(e, p);
			return null;
		}
		
		// 检查变量命名是否合法
		@Override
		public Void visitVariable(VariableElement e, Void p) {
			// 如果这个Variable是枚举或变量，则按大写命名检查，否则按照驼式命名法规范检查
			if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e))
				checkAllCaps(e);
			else 
				checkCamelCases(e, false);
			return null;
		}
		
		// 判断一个变量是否为常量
		private boolean heuristicallyConstant(VariableElement e) {
			if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE)
				return true;
			else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)))
				return true;
			else 
				return false;
		}
		
		// 检查传入的Element是否符合驼式命名法、如果不符合，则输出警告信息
		private void checkCamelCases(Element e, boolean initialCaps) {
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(Character.isUpperCase(firstCodePoint)) {
				previousUpper = true;
				if(!initialCaps) {
					message.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
					return ;
				}
			} else if(Character.isLowerCase(firstCodePoint)) {
				if(!initialCaps) {
					message.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
					return ;
				}
			} else 
				conventional = false;
			if(conventional) {
				int cp = firstCodePoint;
				for(int i=Character.charCount(cp); i<name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if(Character.isUpperCase(cp)) {
						if(previousUpper) {
							conventional = false;
							break;
						}
						previousUpper = true;
					} else 
						previousUpper = false;
				}
				
			}
			if(!conventional)
				message.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以符合驼式命名法(Camel Case names)", e);
		}
		
		// 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母
		private void checkAllCaps(Element e) {
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(!Character.isUpperCase(firstCodePoint))
				conventional = false;
			else {
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				for(int i = Character.charCount(cp); i<name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if(cp == (int) '_') {
						if(previousUnderscore) {
							conventional = false;
							break;
						}
						previousUnderscore = true;
					} else {
						previousUnderscore = false;
						if(!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventional = false;
							break;
						}
					}
				}
			}
			if(!conventional)
				message.printMessage(Diagnostic.Kind.WARNING, "常量" + name + "应当全部以大写字母或下划线命名，并且以字母开头", e);
			
		}
	}
}