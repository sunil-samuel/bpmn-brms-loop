package com.sunilsamuel.bpmn.bpmn_brms_diverge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.core.runtime.rule.impl.FlatQueryResults;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieContext;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.process.ProcessContext;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.command.CommandFactory;

public class RulesTest {
	@Test
	public void stateless() {
		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		List<Command<?>> commands;
		 org.drools.core.base.DefaultKnowledgeHelper a;
		 
		// org.drools.core.spi.ProcessContext
		ProcessContext  kcontext;
		org.jbpm.ruleflow.instance.RuleFlowProcessInstance b;
		
		commands = new ArrayList<Command<?>>();
		System.out.println("HERE STARTING");

		commands.add(CommandFactory.newStartProcess("com.sunilsamuel.bpmn.bpmn_brms_diverge.EmployeeProcess"));
		commands.add(CommandFactory.newFireAllRules());
		commands.add(CommandFactory.newQuery("$employees", "get_employees"));
		StatelessKieSession ksession = kieContainer.newStatelessKieSession("diversionStateLess");
		System.out.println("HERE AGAIN");

		BatchExecutionCommand batch = CommandFactory.newBatchExecution(commands);
		System.out.println("HERE AGAIN2");

		ExecutionResults results = ksession.execute(batch);
		System.out.println("HERE AGAIN3");

		FlatQueryResults queryResults = (FlatQueryResults) results.getValue("$employees");
		Iterator<QueryResultsRow> iterator = queryResults.iterator();
		List<Employee> employees = (List<Employee>) (iterator.hasNext() ? iterator.next().get("$employees") : null);
		System.out.println("HERE SUNIL [" + employees + "]");
	}


}
