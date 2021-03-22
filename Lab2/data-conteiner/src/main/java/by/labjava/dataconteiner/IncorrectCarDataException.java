package by.labjava.dataconteiner;

import org.apache.commons.lang3.StringUtils;

public class IncorrectCarDataException extends Exception {

    public IncorrectCarDataException(String fieldName) {
        super(fieldName + StringUtils.SPACE + "is empty");
    }


}
