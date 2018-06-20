package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.Node;
import com.migu.schedule.info.Task;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {
    List<Node> nodes = new ArrayList<Node>();
    List<Task> tasks = new ArrayList<Task>();
    List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();


    public int init() {
        nodes = new ArrayList<Node>();
        tasks = new ArrayList<Task>();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            Node node = (Node)it.next();
            if (node.getNodeId() == nodeId) {
                return ReturnCodeKeys.E005;
            }
        }
        nodes.add(new Node(nodeId));
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        boolean flag = false;
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            Node node = (Node)it.next();
            if (node.getNodeId() == nodeId) {
                flag = true;
            }
        }
        if (!flag) {
            return ReturnCodeKeys.E007;
        }
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            Node node = (Node)it.next();
            if (node.getNodeId() == nodeId) {
                it.remove();
            }
        }
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        for (Iterator it = tasks.iterator(); it.hasNext();) {
            Task task = (Task)it.next();
            if (task.getTaskId() == taskId) {
                return ReturnCodeKeys.E010;
            }
        }
        tasks.add(new Task(taskId,consumption));
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        boolean flag = false;
        for (Iterator it = tasks.iterator(); it.hasNext();) {
            Task task = (Task)it.next();
            if (task.getTaskId() == taskId) {
                flag = true;
            }
        }
        if (!flag) {
            return ReturnCodeKeys.E012;
        }
        for (Iterator it = tasks.iterator(); it.hasNext();) {
            Task task = (Task)it.next();
            if (task.getTaskId() == taskId) {
                it.remove();
            }
        }
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        if (threshold <= 0) {
            return ReturnCodeKeys.E002;
        }
        int[] tempNode = new int[nodes.size()];
        for (Task task :tasks) {
            int index = findMinIndex(tempNode);
            for(int i = 0;i< tempNode.length;i++) {
                if (tempNode[i] + task.getConsumption() < threshold) {
                    tempNode[i] += task.getConsumption();
                    index = i;
                    break;
                }
            }
            taskInfos.add(new TaskInfo(task.getTaskId(),index));
        }
        if (flag(tempNode,threshold)) {
            return ReturnCodeKeys.E014;
        }
        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        tasks = this.taskInfos;
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

    private int findMinIndex (int [] array) {
        int minIndex = 0;
        int min=array[0];
        for(int i=0;i<array.length;i++) {
            if(array[i]<min) {
                min=array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private boolean flag (int [] array ,int a) {
        int min = array[0];
        int max = array[0];
        for(int i=0;i<array.length;i++) {
            if(array[i]>max)
                max=array[i];
            if(array[i]<min)
                min=array[i];
        }
        return max - min > a;
    }


}
