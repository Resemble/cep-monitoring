package org.stsffap.cep.monitoring;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.stsffap.cep.monitoring.events.LoginEvent;
import org.stsffap.cep.monitoring.events.LoginWarning;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description:
 * @date 2020-12-02 19:58
 */
public class CEPTest {

    private static final String FAIL_STATUS = "fail";
    private static final String SUCCESS_STATUS = "success";

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<LoginEvent> eventStream = env.fromCollection(Arrays.asList(
            new LoginEvent("1","192.168.0.1",FAIL_STATUS),
            new LoginEvent("1","192.168.0.2",FAIL_STATUS),
            new LoginEvent("1","192.168.0.3",FAIL_STATUS),
            new LoginEvent("2","192.168.10,10",SUCCESS_STATUS)
        ));
        Pattern<LoginEvent, LoginEvent> loginFailPattern = Pattern.<LoginEvent>
            begin("begin")
            .where(new IterativeCondition<LoginEvent>() {

                @Override public boolean filter(LoginEvent value, Context<LoginEvent> ctx)
                    throws Exception {
                    return FAIL_STATUS.equals(value.getType());
                }
            })
            .next("next")
            .where(new IterativeCondition<LoginEvent>() {

                @Override public boolean filter(LoginEvent value, Context<LoginEvent> ctx)
                    throws Exception {
                    return FAIL_STATUS.equals(value.getType());
                }
            }).within(Time.seconds(3));
        PatternStream<LoginEvent> patternStream = CEP
            .pattern(eventStream.keyBy(loginEvent -> loginEvent.getUserId()), loginFailPattern);
        DataStream<LoginWarning> loginFailDataStream = patternStream.select((Map<String, List<LoginEvent>> pattern) -> {
            List<LoginEvent> first = pattern.get("begin");
            List<LoginEvent> second = pattern.get("next");
            System.out.println("first:" + first.get(0).toString());
            System.out.println("second:" + second.get(0).toString());
            return new LoginWarning(second.get(0).getUserId(), second.get(0).getIp(), second.get(0).getType());
        });
        loginFailDataStream.print();
        env.execute();
    }

}
