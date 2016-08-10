package metacomp;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wildy
 */
public class Compi {
    
    private enum Operator
    {
        SUMA(1), RESTA(1), MULTI(2), DIV(2),NULO(0);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.SUMA);
        put("-", Operator.RESTA);
        put("*", Operator.MULTI);
        put("/", Operator.DIV);
	put(":", Operator.NULO);
    }};
    
    private String [] pl = {":"};
    private String [] sl = {};
    
    private LinkedList< String > pila = new LinkedList< String > ( Arrays.asList(pl) );
    private LinkedList< String > salida = new LinkedList< String > ( Arrays.asList(sl) );
    
    public void scan( String[] expresion ){
        // mientras haya tokens
        for (String token : expresion){
        
            //si el token es un operador
            if (ops.containsKey(token))
            {
                if((ops.isEmpty()))
                {
			pila.addFirst(token);
                }//fin de if isEmpty
                else
		{
                    //mientras el token sea menor o igual que el operador arriba de la pila
                    while(ops.get(token).precedence <= ops.get(pila.getFirst()).precedence)
                    {
                        salida.add(pila.getFirst());
                        pila.removeFirst();
                
                    }//fin de while
                    pila.addFirst(token);

		}//fin else
            
            }//fin de if
            else
                salida.add(token);
        }// fin de for
        
        
    }// fin metodo scan
    
    public String getSalida(){
	for(String t : pila.toArray(new String[ pila.size() ]))
		{if (!(t == ":"))
			salida.add(t);}
        
        String str = "";
        
        for(String s : salida.toArray(new String[ salida.size() ]))
        {
            str += s;
        }//fin for 2

        return str;
    }//fin metodo getSalida
    
}// fin clase compi