package wangrong.wr1.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@ConfigurationProperties(prefix="redis")
public class RedisConfig {

	private String host="127.0.0.1";
	private int port=6379;
	private int timeout=3;
	private String password="1234";
	private int poolMaxTotal=10;
	private int poolMaxIdle=10;
	private int poolMaxWait=3;
	
	public RedisConfig()
	{
		
	}
	
	public String getHost() 
	{
					return host;
		}
													public void setHost(String host) {
														this.host = host;
													}
													public int getPort() {
														return port;
													}
													public void setPort(int port) {
														this.port = port;
													}
													public int getTimeout() {
														return timeout;
													}
													public void setTimeout(int timeout) {
														this.timeout = timeout;
													}
													public String getPassword() {
														return password;
													}
													public void setPassword(String password) {
														this.password = password;
													}
													public int getPoolMaxTotal() {
														return poolMaxTotal;
													}
													public void setPoolMaxTotal(int poolMaxTotal) {
														this.poolMaxTotal = poolMaxTotal;
													}
													public int getPoolMaxIdle() {
														return poolMaxIdle;
													}
													public void setPoolMaxIdle(int poolMaxIdle) {
														this.poolMaxIdle = poolMaxIdle;
													}
													public int getPoolMaxWait() {
														return poolMaxWait;
													}
													public void setPoolMaxWait(int poolMaxWait) {
														this.poolMaxWait = poolMaxWait;
													}
													public RedisConfig(String host, int port, int timeout,
															String password, int poolMaxTotal, int poolMaxIdle,
															int poolMaxWait) {
														super();
														this.host = host;
														this.port = port;
														this.timeout = timeout;
														this.password = password;
														this.poolMaxTotal = poolMaxTotal;
														this.poolMaxIdle = poolMaxIdle;
														this.poolMaxWait = poolMaxWait;
													}
													
													
	
	
}
