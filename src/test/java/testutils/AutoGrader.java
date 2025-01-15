package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.ExpressionStmt;

public class AutoGrader {

	// Test if the code uses proper method creation and usage
	public boolean testMethodOperationsOnly(String filePath) throws IOException {
		System.out.println("Starting testMethodOperationsOnly with file: " + filePath);

		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		boolean hasMethodOperations = false;

		// Check for method declarations
		System.out.println("------ Method Declarations ------");
		for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
			System.out.println("Method found: " + method.getNameAsString());
			hasMethodOperations = true;
		}

		// Check for method calls
		System.out.println("------ Method Calls ------");
		for (MethodCallExpr methodCall : cu.findAll(MethodCallExpr.class)) {
			System.out.println("Method call found: " + methodCall.getNameAsString());
			hasMethodOperations = true;
		}

		// Check for method references
		System.out.println("------ Method References ------");
		for (MethodReferenceExpr methodReference : cu.findAll(MethodReferenceExpr.class)) {
			System.out.println("Method reference found: "
					+ ((NodeWithSimpleName<MethodDeclaration>) methodReference).getNameAsString());
			hasMethodOperations = true;
		}

		// Check if a method calls another method
		System.out.println("------ Method Calling Another Method ------");
		for (ExpressionStmt stmt : cu.findAll(ExpressionStmt.class)) {
			if (stmt.getExpression() instanceof MethodCallExpr) {
				MethodCallExpr methodCall = (MethodCallExpr) stmt.getExpression();
				String methodName = methodCall.getNameAsString();
				if (methodName.equals("printMessage") || methodName.equals("addNumbers") || methodName.equals("findMax")
						|| methodName.equals("calculateSum")) {
					System.out.println("Method calling another method: " + methodName);
					hasMethodOperations = true;
				}
			}
		}

		// Return whether method-related operations are found
		System.out.println("Has correct method operations: " + hasMethodOperations);

		boolean result = hasMethodOperations;
		System.out.println("Test result: " + result);

		return result;
	}
}
