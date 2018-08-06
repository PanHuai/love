/**
 * Title:
 * Description:
 * @author yangwenkui
 * @version v1.0
 * @time 2016年5月6日 上午10:52:58
 */
package com.love.lylph.common.lock;

/**
 * Title: 分布式锁接口
 * @author yangwenkui
 * @version v1.0
 * @time 2016年5月6日 上午10:52:58
 */
public interface DistributedLock {
	
	/**
	 * 获取锁
	 * @author yangwenkui
	 * @time 2016年5月6日 上午11:02:54
	 * @return
	 * @throws InterruptedException
	 */
	public boolean acquire();
	
	/**
	 * 释放锁
	 * @author yangwenkui
	 * @time 2016年5月6日 上午11:02:59
	 */
	public void release();
	
}
