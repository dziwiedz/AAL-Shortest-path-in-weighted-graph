package Model;

import java.util.concurrent.TimeUnit;

/**
 * Created by Gulgulgulgul on 26.05.2016.
 */
public class Timer
{
    private long startTime;
    private long elapsedTime;
    public Timer()
    {

    }
    public void startTimer()
    {
        startTime = getCurrentTime();
    }
    public void stopTimer()
    {
        elapsedTime = getCurrentTime() - startTime;
    }
    public double getTime()
    {
        return (double)TimeUnit.MILLISECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);
    }


    private static final long getCurrentTime() {
        return System.nanoTime();
    }
}
