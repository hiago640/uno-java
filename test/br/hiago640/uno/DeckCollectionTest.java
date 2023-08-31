package br.hiago640.uno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.Test;

class DeckCollectionTest {

	@Test
	void stackTest() {
		Stack<Integer> pilha = new Stack<>();
		int n1 = 1;
		int n2 = 2;
		int n3 = 3;

		assertEquals(0, pilha.size());
		assertTrue(pilha.empty());

		pilha.push(n1);
		assertEquals(1, pilha.size());

		pilha.push(n2);
		assertEquals(2, pilha.size());

		System.out.println(pilha);

		assertEquals(n2, pilha.peek());
		assertEquals(n2, pilha.pop());

		pilha.push(n3);
		System.out.println(pilha);

		assertEquals(2, pilha.size());
	}

}
