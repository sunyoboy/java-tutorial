package com.javase.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 12/31/16.
 */
public class SayHelloToLeaveTest {
    @Test
    public void testStartProcess() {
        // 创建流程引擎, 使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration // #1-S
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine(); //#1-E

        // 部署流程定义文件
        RepositoryService repositoryService = processEngine
                .getRepositoryService(); // #2
/*        repositoryService.createDeployment() // #3-S
                .addClasspathResource("leave.bpmn") // #3-E
                .deploy();*/
        repositoryService.createDeployment().addInputStream("src/main/resources/leave.bpmn",
                this.getClass().getClassLoader().getResourceAsStream("leave.bpmn")).deploy();

        // 验证已部署流程定义
        ProcessDefinition processDefinition = repositoryService // #4-S
                .createProcessDefinitionQuery().singleResult();
        org.junit.Assert.assertEquals("leave", processDefinition.getKey());
        // 启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUser","employee1");
        variables.put("days",3);

        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("leave", variables);
        org.junit.Assert.assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() +",pdid=" +
                processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        Task taskOfDeptLeader = taskService.createTaskQuery()
                .taskCandidateGroup("deptLeader").singleResult();
        org.junit.Assert.assertNotNull(taskOfDeptLeader);
        org.junit.Assert.assertEquals("领导审批", taskOfDeptLeader.getName());
        taskService.claim(taskOfDeptLeader.getId(), "deptLeader");
        variables = new HashMap<String, Object>();
        variables.put("approval", true);
        taskService.complete(taskOfDeptLeader.getId(), variables);
        taskOfDeptLeader = taskService.createTaskQuery()
                .taskCandidateGroup("deptLeader").singleResult();
        org.junit.Assert.assertNull(taskOfDeptLeader);
        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery()
                .finished().count();
        org.junit.Assert.assertEquals(1, count);
    }
}
