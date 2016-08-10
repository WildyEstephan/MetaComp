/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metacomp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author wildy
 */
public class AbstractMachine {
    private enum Operator
    {
        SUMA(1), RESTA(1), MULTI(2), DIV(2);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.SUMA);
        put("-", Operator.RESTA);
        put("*", Operator.MULTI);
        put("/", Operator.DIV);
    }};
    
    private String [] pl = {};
    
    private LinkedList< String > pila = new LinkedList< String > ( Arrays.asList(pl) );
    private LinkedList< String > salida;
    
    public void setSalida( String[] sal ){
        
        salida = new LinkedList< String > (Arrays.asList(sal));
        
    }//fin metodo setSalida
    
    public void ejecutar(){
    
        // si el token es un operador hacer la operacion adecuada
        for (String token : salida.toArray( new String[ salida.size() ] ))
        {
            // si es un operador hacer la operacion adecuada
            if (ops.containsKey(token)){
            
                pila.add(operar( pila.getLast(), pila.getFirst(), token ));
                
            }//fin de if
            else //sino agregar a la pila
            {
                pila.add(token);
            }
        
        }// fin de for mejorado
             
    }// fin de metodo ejecutar
    
    private String operar( String numDer, String numIzq, String operador){
    
        int numD = Integer.parseInt(numDer);
        int numI = Integer.parseInt(numIzq);
        int resultado = 0;
        
        //Depende del operador
        switch(operador){
        
            case "*":
                resultado = numI * numD;
                break;
            case "/":
                resultado = numI / numD;
                break;
            case "+":
                resultado = numI + numD;
                break;
            case "-":
                resultado = numI - numD;
                break;
        
        }// fin de switch
        
        return String.valueOf(resultado);
        
        
    }//fin metodo operar
    
    public String getResultado(){
    
        return pila.getLast();
    
    }//fin de getResultado
    
}// fin de clase Compi
