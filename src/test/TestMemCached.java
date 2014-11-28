package test;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TestMemCached {

	protected static MemCachedClient mcc = new MemCachedClient();

	static {
		String[] servers = { "localhost:11211" };

		Integer[] weights = { 3 };

		// ����һ��ʵ������SockIOPool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		// ����Memcached Server
		pool.setServers(servers);
		pool.setWeights(weights);

		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);

		// Tcp�Ĺ�������ڷ���һ����֮ǰ�����ػ�����ȴ�Զ������
		// ����һ�η��͵İ���ȷ����Ϣ��������������Ϳ��Թر��׽��ֵĻ��棬
		// ���������׼�����˾ͷ���
		pool.setNagle(false);
		// ���ӽ�����Գ�ʱ�Ŀ���
		pool.setSocketTO(3000);
		// ���ӽ���ʱ�Գ�ʱ�Ŀ���
		pool.setSocketConnectTO(0);

		// initialize the connection pool
		// ��ʼ��һЩֵ����MemcachedServer�ν�������
		pool.initialize();

		// lets set some compression on for the client
		// compress anything larger than 64k
		// mcc.setCompressEnable(true);
		// mcc.setCompressThreshold(64 * 1024);
	}

	public static void bulidCache() {
		// set(key,value,Date) ,Date��һ������ʱ�䣬��������������ʱ����Ч�Ļ������ﴫ�ݵ�new Date(long
		// date) �в���date����Ҫ�Ǹ����ڻ����1000��ֵ��
		// ��Ϊjava client��ʵ��Դ����������ʵ�ֵ� expiry.getTime() / 1000 ��Ҳ����˵�����
		// С��1000��ֵ������1000�Ժ���0������������
		TMember t = new TMember();
		t.setId("123456789");
		t.setName("yangliang");
		mcc.set("test", t, new Date(100000)); // ʮ������

	}

	public static void output() {
		// ��cache��ȡֵ
		TMember value = (TMember) mcc.get("test");
		System.out.println(value.getId());
		System.out.println(value.getName());
		// String value = (String) mcc.get("test");
		// System.out.println(value);
	}

	public static void main(String[] args) {
		bulidCache();
		output();
	}

}