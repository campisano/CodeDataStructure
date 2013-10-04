import java.io.IOException;
import java.io.InputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import java.io.PrintWriter;
import org.objectweb.asm.util.TraceClassVisitor;

class Bar
{
}

class Foo extends Bar {
	private int a[] = new int[132], b[], c[];

	public void run() {
		for (int i = 0; i < a.length && i < b.length && i < c.length; ++i) {
			c[i] = a[i] + b[i];
		}
	}
}

public class TestASMPrint {
	public static void main(String[] args) throws IOException {
		InputStream in = TestASMPrint.class.getResourceAsStream("Foo.class");
		ClassReader classReader = new ClassReader(in);
		ClassVisitor cl = new MyClassVisitor(Opcodes.ASM4);
		classReader.accept(cl, 0);

		cl = new TraceClassVisitor(new PrintWriter(System.out));
		classReader.accept(cl, 0);
	}
}