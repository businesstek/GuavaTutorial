package com.bt.guavatutorial;

/**
 * Created by owner on 7/4/17.
 */

/**
 * Guava Basic Utilties
 * Optional
 * Preconditions
 * Ordering
 * Objects
 * Throwables
 */

import android.util.Log;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.math.RoundingMode.FLOOR;

public class GuavaBasic1 {

    public GuavaBasic1() {

    }


    /**
     * Optional
     */
    private Integer value1 = null;
    private Integer value2 = 10;
    Optional<Integer> a = Optional.fromNullable(value1);
    Optional<Integer> b = Optional.of(value2);

    public void guavaOptional() {
        GuavaBasic guavaBasic = new GuavaBasic();
        Log.i(MainActivity.TAG, guavaBasic.sum(a, b).toString());
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        String str = "false";
        if (b.isPresent()) {
            str = "true";
        }
        Log.i(MainActivity.TAG, "Second parameter is present: " + str);
        return value1 + value2;
    }

    /**
     * Preconditions
     */

    public void guavaPreconditions() {
        GuavaBasic guavaBasic = new GuavaBasic();
        try {
            Log.i(MainActivity.TAG, String.valueOf(guavaBasic.sqrtPre(-3.0)));
        } catch (IllegalArgumentException e) {
            Log.i(MainActivity.TAG, e.getMessage());
        }
        try {
            Log.i(MainActivity.TAG, String.valueOf(guavaBasic.sumPre(null, 3)));
        } catch (NullPointerException e) {
            Log.i(MainActivity.TAG, e.getMessage());
        }
        try {
            Log.i(MainActivity.TAG, String.valueOf(guavaBasic.getValuePre(6)));
        } catch (IndexOutOfBoundsException e) {
            Log.i(MainActivity.TAG, e.getMessage());
        }
    }


    public double sqrtPre(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    public int sumPre(Integer a, Integer b) {
        a = Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b,
                "Illegal Argument passed: Second parameter is Null.");
        return a + b;
    }

    public int getValuePre(int input) {
        int[] data = {1, 2, 3, 4, 5};
       int a = Preconditions.checkElementIndex(input, data.length,
                "Illegal Argument passed: Invalid index.");
        return 0;
    }


    /**
     * Ordering
     */
    private void guavaOrdering() {
        final Integer[] items = {5, 2, 15, 51, 53, 35, 45, 32, 43, 16};
        List<Integer> numbers = new ArrayList<Integer>();
        for (Integer item: items){
            numbers.add(Integer.valueOf(item));
        }

        Ordering ordering = Ordering.natural();
        Log.i(MainActivity.TAG,"Input List: ");
        Log.i(MainActivity.TAG,numbers.toString());
        Collections.sort(numbers, ordering);
        Log.i(MainActivity.TAG,"Sorted List: ");
        Log.i(MainActivity.TAG,numbers.toString());
        Log.i(MainActivity.TAG,"======================");
        Log.i(MainActivity.TAG,"List is sorted: " + ordering.isOrdered(numbers));
        Log.i(MainActivity.TAG,"Minimum: " + ordering.min(numbers));

    }


    /**
     * Objects
     */

    public void guavaObjects(){
        Student s1 = new Student("Mahesh", "Parashar", 1, "VI");
        Student s2 = new Student("Suresh", null, 3, null);
        Log.i(MainActivity.TAG,(s1.equals(s2)?"true":"false"));
        Log.i(MainActivity.TAG,String.valueOf(s1.hashCode()));
        Log.i(MainActivity.TAG,
                MoreObjects.toStringHelper(s1)
                        .add("Name",s1.getFirstName()+" " + s1.getLastName()) .add("Class", s1.getClassName())
                        .add("Roll No", s1.getRollNo())
                        .toString());
    }

    private class Student{
        private String firstName;
        private String lastName;
        private int rollNo;
        private String className;
        Student(String firstName, String lastName, int rollNo, String className){
            this.firstName = firstName;
            this.lastName = lastName;
            this.rollNo = rollNo;
            this.className = className;
        }

        @Override
        public boolean equals(Object object){
            if(!(object instanceof Student)){
                return false;
            }
            Student student = (Student)object;
// no need to handle null here
// Objects.equal("test", "test") == true
// Objects.equal("test", null) == false
// Objects.equal(null, "test") == false
// Objects.equal(null, null) == true
            return Objects.equal(firstName, student.firstName) // first name can be null
                    && Objects.equal(lastName, student.lastName) // last name can be null && Objects.equal(rollNo, student.rollNo)
                    && Objects.equal(className, student.className);// class name can be null
        }
        @Override
        public int hashCode(){
            //no need to compute hashCode by self
            return Objects.hashCode(className,rollNo);
        }
        String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        int getRollNo() {
            return rollNo;

        }
        public void setRollNo(int rollNo) {
            this.rollNo = rollNo;
        }
        String getClassName() {
            return className;
        }
        public void setClassName(String className) {
            this.className = className;
        }
    }


    /**
     * Throwables
     *
     * Guava provides several utilities to simplify propagating exceptions.
     * and
     * Guava provides more information to causal chain
     *      Throwable getRootCause(Throwable)
     *      List<Throwable> getCausalChain(Throwable)
     *      String getStackTraceAsString(Throwable)
     *
     */



}

