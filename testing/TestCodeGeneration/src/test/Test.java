package test;

import java.util.Random;

import model.*;
import model.statements.*;
import model.expressions.*;
import model.expressions.condition.*;

public class Test {

	public static void main(String[] args) {
		try {
			testAssignment();
			testAdd();
		} catch (Exception e) {
			System.err.println(e.getMessage());

			return;
		}

		TestData data = new TestData(8);
		{
			CustomProgram cp = new CustomProgram(data);
			cp.run();
			data.print();
		}
		data.clean();
		{
			// equivalent code data structure :
			Block main_block = new Block();
			{
				// Long a[] = m_in_out_put.getA();
				VarExpression<Long[]> input_a;
				{
					input_a = new VarExpression<Long[]>();
					AssignmentStatement<Long[]> input_assing_a = new AssignmentStatement<Long[]>(
							input_a, new ConstExpression<Long[]>(data.getA()));
					main_block.add(input_assing_a);
				}

				// Long b[] = m_in_out_put.getB();
				VarExpression<Long[]> input_b;
				{
					input_b = new VarExpression<Long[]>();
					AssignmentStatement<Long[]> input_assing_b = new AssignmentStatement<Long[]>(
							input_b, new ConstExpression<Long[]>(data.getB()));
					main_block.add(input_assing_b);
				}

				// Long c[] = m_in_out_put.getC();
				VarExpression<Long[]> input_c;
				{
					input_c = new VarExpression<Long[]>();
					AssignmentStatement<Long[]> input_assing_c = new AssignmentStatement<Long[]>(
							input_c, new ConstExpression<Long[]>(data.getC()));
					main_block.add(input_assing_c);
				}

				// int length = m_in_out_put.getDataLength();
				VarExpression<Integer> input_length;
				{
					input_length = new VarExpression<Integer>();
					I_Statement input_len = new AssignmentStatement<Integer>(
							input_length, new ConstExpression<Integer>(
									data.getDataLength()));
					main_block.add(input_len);
				}

				// int i = 0;
				VarExpression<Integer> while_index = new VarExpression<Integer>();
				{
					AssignmentStatement<Integer> while_index_assignment = new AssignmentStatement<Integer>(
							while_index, new ConstExpression<Integer>(0));
					main_block.add(while_index_assignment);
				}
				;

				// while
				WhileStatement while_input;
				{
					// (i < length)
					LessThenCondition<Integer> while_input_condition = new LessThenCondition<Integer>(
							while_index, input_length);

					// {
					Block while_input_block = new Block();
					{
						// while (i < length) {
						while_input = new WhileStatement(while_input_condition,
								while_input_block);
						{
							// c[i] = a[i] + b[i];
							{
								VarExpression<Long> array_item_a = new ArrayItemValue<Long>(
										input_a, while_index);
								VarExpression<Long> array_item_b = new ArrayItemValue<Long>(
										input_b, while_index);
								VarExpression<Long> array_item_c = new ArrayItemValue<Long>(
										input_c, while_index);
								LongAddExpression add_a_b = new LongAddExpression(
										array_item_a, array_item_b);
								AssignmentStatement<Long> output_c = new AssignmentStatement<Long>(
										array_item_c, add_a_b);
								while_input_block.add(output_c);
							}

							// ++i;
							{
								IntegerAddExpression while_index_increment = new IntegerAddExpression(
										while_index,
										new ConstExpression<Integer>(1));
								AssignmentStatement<Integer> index_increment = new AssignmentStatement<Integer>(
										while_index, while_index_increment);
								while_input_block.add(index_increment);
							}
						}
					}
				}
				main_block.add(while_input);
			}

			main_block.execute();
			data.print();
		}
	}

	private static void testAssignment() throws Exception {
		int k = 3;
		int a;

		VarExpression<Integer> v_a = new VarExpression<Integer>();
		AssignmentStatement<Integer> out_a = new AssignmentStatement<Integer>(
				v_a, new ConstExpression<Integer>(k));

		out_a.execute();
		a = v_a.evaluate();

		if (k != a) {
			throw new Exception("testAssignment() failed!");
		}
	}

	private static void testAdd() throws Exception {
		int a;
		int b = 3;
		int c = 5;

		VarExpression<Integer> v_a = new VarExpression<Integer>();
		ConstExpression<Integer> c_b = new ConstExpression<Integer>(b);
		ConstExpression<Integer> c_c = new ConstExpression<Integer>(c);
		IntegerAddExpression add_b_c = new IntegerAddExpression(c_b, c_c);
		AssignmentStatement<Integer> out_a = new AssignmentStatement<Integer>(
				v_a, add_b_c);

		out_a.execute();
		a = v_a.evaluate();

		if (b + c != a) {
			throw new Exception("testAdd() failed!");
		}
	}
}

// ---------------------------------------------------------
// generated interface from file input specified by the user
interface InOutPut {
	int getDataLength();

	Long[] getA();

	Long[] getB();

	Long[] getC();
}

// end
// ---------------------------------------------------------

// ---------------------------------------------------------
// user custom program
class CustomProgram {
	private InOutPut m_in_out_put;

	public CustomProgram(InOutPut _in_out_put) {
		m_in_out_put = _in_out_put;
	}

	public void run() {
		Long a[] = m_in_out_put.getA();
		Long b[] = m_in_out_put.getB();
		Long c[] = m_in_out_put.getC();
		int length = m_in_out_put.getDataLength();

		int i = 0;
		while (i < length) {
			c[i] = a[i] + b[i];
			++i;
		}
	}
}

// end
// ---------------------------------------------------------

// ---------------------------------------------------------
// backend test data class
class TestData implements InOutPut {

	private Long[] m_a;
	private Long[] m_b;
	private Long[] m_c;
	private int m_length;

	public TestData(int _length) {
		m_length = _length;
		m_a = new Long[m_length];
		m_b = new Long[m_length];
		m_c = new Long[m_length];

		Random ran = new Random();

		for (int i = 0; i < m_length; ++i) {
			m_a[i] = Math.abs(ran.nextLong()) % m_length;
			m_b[i] = m_length - m_a[i];
			m_c[i] = new Long(0);
		}
	}

	public void clean() {
		for (int i = 0; i < m_length; ++i) {
			m_c[i] = new Long(0);
		}
	}

	public void print() {
		System.out.println("\nC data:");
		for (int i = 0; i < m_length; ++i) {
			System.out.print(i + ": " + m_a[i]);
			System.out.print(" oper " + m_b[i]);
			System.out.println(" = " + m_c[i]);
		}
	}

	public int getDataLength() {
		return m_length;
	}

	public Long[] getA() {
		return m_a;
	}

	public Long[] getB() {
		return m_b;
	}

	public Long[] getC() {
		return m_c;
	}
}
// end
// ---------------------------------------------------------
