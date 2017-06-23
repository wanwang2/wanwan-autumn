package org.wanwanframework.autumn.logupdate;

public class PatternConfig {
	public static final String namePattern = "[_a-zA-Z]+\\w*";
    public static final String templatePattern = "(<[_a-zA-Z]+\\w*(, [_a-zA-Z]+\\w*)*>)?";
    public static final String typePattern = namePattern + templatePattern;

    // 参数的正则
    public static final String argPatternZero = "";       // 无参数
    public static final String argPatternDynamic = "..."; // 动态参数
    public static final String argPattern = typePattern + " " + namePattern;                     // 一个参数
    public static final String argPatternOne = argPattern + "(, ...)?";                          // 一个参数 ＋ 动态参数
    public static final String argPatternMulti = argPattern + "(, " + argPattern + ")*(, ...)?"; // 多个参数 ＋ 动态参数
    public static final String argsPattern = "(" + argPatternZero + "|" + argPatternDynamic + "|" + argPatternOne + "|" + argPatternMulti + ")";

    // 方法签名的正则
    public static final String methodSignaturePattern = "(public|protected|private)?(\\s+static)?(\\s+final)?\\s*" +
            typePattern + "\\s+" + namePattern + "\\(\\s*" + argsPattern + "\\s*\\)";
    
    // 方法识别的正则
    public static final String methodName = namePattern + "\\(\\s*" + argsPattern + "\\s*\\)";
    
    public static void main(String[] args) {
    	 // 每个部分的正则测试
        System.out.println("List<int, Object>".matches(typePattern));
        System.out.println("".matches(argPatternZero));
        System.out.println("...".matches(argPatternDynamic));
        //System.out.println(argPatternOne);
        System.out.println("int age".matches(argPatternOne));
        //System.out.println(argPatternMulti);
        System.out.println("int age, List<Integer> values".matches(argPatternMulti));
        System.out.println("int age, List<Integer> values, ...".matches(argsPattern));
 
        // 测试是否一个方法的签名
        // 暂时规则，单词间只用一个空格分隔，因为实现情况要考虑回车，多个空格，tab键等，把空格在正则中换成\\s+
        // 例如要在','前后加\\s*，函数签名中的'(', ')'前后加\\s*
        // 这里为了不使正则变得太难懂，直接只用了一个空格.
        //System.out.println(methodSignaturePattern);
        System.out.println("public void foo(...)".matches(methodSignaturePattern));
        System.out.println("void foo(...)".matches(methodSignaturePattern));
        System.out.println("void foo(int a )".matches(methodSignaturePattern));
        System.out.println("public List<Integer> foo(String name, Set<Double> values, ...)".matches(methodSignaturePattern));
	}
}
