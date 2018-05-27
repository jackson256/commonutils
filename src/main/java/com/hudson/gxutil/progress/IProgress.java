/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.progress
 * @author: huhan
 * @date:2018年5月27日 下午12:58:54
 */
package com.hudson.gxutil.progress;

 /** 
 * @className: IProgress
 * @Description:
 * @author: hudson
 * @date: 2018年5月27日 下午12:58:54
 * @version: 1.0
 */
public abstract interface IProgress
{
  public abstract <X> void resfresh(X... paramVarArgs);
}
