package main;

import java.time.LocalDateTime;

public class Result {
    LocalDateTime time;
    boolean result;
    public Result(LocalDateTime time, boolean result){
        this.time = time;
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
