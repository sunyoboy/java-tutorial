package com.javase.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessDefinitionQueryProperty;
import org.activiti.engine.impl.cmd.SaveAttachmentCmd;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by root on 12/31/16.
 */
public class VerySimpleLeaveProcessTest {

    @Test
    public void testStartProcess() throws Exception {
        // 创建流程引擎, 使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration // #1-S
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine(); //#1-E

        // 部署流程定义文件
        RepositoryService repositoryService = processEngine
                .getRepositoryService(); // #2
        repositoryService.createDeployment() // #3-S
        .addClasspathResource("leave.bpmn") // #3-E
        .deploy();

        // 验证已部署流程定义
        ProcessDefinition processDefinition = repositoryService // #4-S
            .createProcessDefinitionQuery().singleResult();
        org.junit.Assert.assertEquals("leave", processDefinition.getKey());
        // 启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("leave");
        org.junit.Assert.assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() +",pdid=" +
                processInstance.getProcessDefinitionId());
    }
}
