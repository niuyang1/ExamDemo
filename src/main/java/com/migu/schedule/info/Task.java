package com.migu.schedule.info;

/**
 * 任务状态信息类，请勿修改。
 *
 * @author
 * @version
 */
public class Task
{
    private int taskId;
    private int consumption;

    public Task (int taskId, int consumption) {
        this.taskId = taskId;
        this.consumption = consumption;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
