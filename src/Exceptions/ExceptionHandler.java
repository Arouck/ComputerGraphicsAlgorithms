package Exceptions;

import Exceptions.ValueException;

public class ExceptionHandler {

    public static void xAndYException(int valueX1, int valueY1, int valueX2, int valueY2,
                                      int XMAXVALUE, int YMAXVALUE, int XMINVALUE, int YMINVALUE) throws ValueException {

        if (valueX1 > XMAXVALUE) {
            throw new ValueException("O valor de x1 é maior que o valor máximo.");
        } else {
            if(valueX1 < XMINVALUE) {
                throw new ValueException("O valor de x1 é menor o valor mínimo.");
            }
        }

        if (valueY1 > YMAXVALUE) {
            throw new ValueException("O valor de y1 é maior que o valor máximo.");
        } else {
            if(valueY1 < YMINVALUE) {
                throw new ValueException("O valor de y1 é menor o valor mínimo.");
            }
        }

        if (valueX2 > XMAXVALUE) {
            throw new ValueException("O valor de x2 é maior que o valor máximo.");
        } else {
            if(valueX2 < XMINVALUE) {
                throw new ValueException("O valor de x2 é menor o valor mínimo.");
            }
        }

        if (valueY2 > YMAXVALUE) {
            throw new ValueException("O valor de y2 é maior que o valor máximo.");
        } else {
            if(valueY2 < YMINVALUE) {
                throw new ValueException("O valor de y2 é menor o valor mínimo.");
            }
        }
    }

    public static void circleValuesException(int radius, int valueXCenter, int valueYCenter,
                                             int WIDTH, int HEIGHT) throws ValueException {

        if ((radius > WIDTH + valueXCenter) || (radius > HEIGHT + valueYCenter)) {
            throw new ValueException("O valor do raio é maior que o valor máximo.");
        } else {
            if((radius < -WIDTH - valueXCenter) || (radius < -HEIGHT - valueYCenter)) {
                throw new ValueException("O valor do raio é menor o valor mínimo.");
            }
        }
    }
}
