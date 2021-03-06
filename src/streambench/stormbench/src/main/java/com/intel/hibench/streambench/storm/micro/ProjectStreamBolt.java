/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.hibench.streambench.storm.micro;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import backtype.storm.tuple.Fields;

public class ProjectStreamBolt extends BaseBasicBolt{
	private int fieldIndex;
	private String separator;
	
	public ProjectStreamBolt(int fieldIndex, String separator){
		this.fieldIndex=fieldIndex;
		this.separator=separator;
	}
	
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String record=tuple.getString(0);
		String[] fields=record.split(separator);
		if(fields.length>fieldIndex){
		  //BenchLogUtil.logMsg(fields[fieldIndex]);
		  collector.emit(new Values(fields[fieldIndex]));
		}
			
	  }
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	  declarer.declare(new Fields("field"));
	}
}
