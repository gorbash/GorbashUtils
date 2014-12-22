package com.gorbash.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

/**
 * Created by Gorbash on 2014-12-22.
 * Test suite for CyclicContainer.
 */
public class CyclicContainerTest {

    @Test
    public void testImmutability() {
        List<String> input = new ArrayList<String>();
        input.add("raz");
        input.add("dwa");
        input.add("trzy");
        CyclicContainer<String> cont = new CyclicContainer<String>(input);
        input.remove("raz");
        input.remove("dwa");
        input.remove("trzy");

        assertThat(input.isEmpty(), is(true));
        assertThat(cont.isEmpty(), is(false));
        assertThat(cont.size(), is(3));
        assertThat(cont.getNext(), is("raz"));
        assertThat(cont.getNext(), is("dwa"));
        assertThat(cont.getNext(), is("trzy"));
    }

    @Test
    public void testOtherTypeContainer() {
        CyclicContainer<Integer> cont = new CyclicContainer<Integer>(Arrays.asList(1, 2));
        assertThat(cont.isEmpty(), is(false));
        assertThat(cont.size(), is(2));
        for (int i = 0; i < 100; i++) {
            assertThat(cont.getNext(), is(1));
            assertThat(cont.getNext(), is(2));
        }
    }


    @Test
    public void testOneElementContainer() {
        CyclicContainer<String> cont = new CyclicContainer<String>(Arrays.asList("test"));
        assertThat(cont.isEmpty(), is(false));
        assertThat(cont.size(), is(1));
        for (int i = 0; i < 100; i++)
            assertThat(cont.getNext(), is("test"));
    }

    @Test
    public void testThreeElementContainer() {
        CyclicContainer<String> cont = new CyclicContainer<String>(Arrays.asList("test", "test2", "test3"));
        assertThat(cont.isEmpty(), is(false));
        assertThat(cont.size(), is(3));
        for (int i = 0; i < 100; i++) {
            assertThat(cont.getNext(), is("test"));
            assertThat(cont.getNext(), is("test2"));
            assertThat(cont.getNext(), is("test3"));
        }
    }

    @Test
    public void testEmptyCyclicContainer() {
        CyclicContainer<Object> cont = new CyclicContainer<Object>(new ArrayList<Object>());
        assertThat(cont.isEmpty(), is(true));
        assertThat(cont.size(), is(0));
        try {
            cont.getNext();
            fail("Empty container should throw exception.");
        } catch (EmptyContainerException e) {
            //it's fine - exception is expected here
        }
    }
}
