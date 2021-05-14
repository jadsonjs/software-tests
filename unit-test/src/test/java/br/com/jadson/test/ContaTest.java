/*
 * Federal University of Rio Grande do Norte
 * Department of Informatics and Applied Mathematics
 * Collaborative & Automated Software Engineering (CASE) Research Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *
 * software-tests
 * br.com.jadson.test
 * ContaTest
 * 14/05/21
 */
package br.com.jadson.test;

/**
 * Test class for Conta class
 * Jadson Santos - jadsonjs@gmail.com
 */
public class ContaTest {

    void testaContaComSaldo(){
        Conta conta = new Conta(10.0d);
        if(conta.temSaldo())
            System.out.println("PASSED");
        else
            System.out.println("FAIL");
    }

    void testaContaSemSaldo(){
        Conta conta = new Conta(0.0d);
        if(conta.temSaldo())
            System.out.println("FAIL");
        else
            System.out.println("PASSED");
    }

    void testaContaComSaldoNulo(){
        try {
            Conta conta = new Conta(null);
            System.out.println("FAIL");
        }catch (IllegalArgumentException iaex){
            System.out.println("PASSED");
        }
    }

    public static void main(String[] args) {
        new ContaTest().testaContaComSaldo();
        new ContaTest().testaContaSemSaldo();
        new ContaTest().testaContaComSaldoNulo();
    }
}


