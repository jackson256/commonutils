/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.progress
 * @author: hudson
 * @date:2018年5月27日 下午12:59:35
 */
package com.hudson.gxutil.progress;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: ConsoleProgressBar
 * @Description:
 * @author: hudson
 * @date: 2018年5月27日 下午12:59:35
 * @version: 1.0
 */
public class ConsoleProgressBar implements IProgress {
	private static final PrintStream out = System.out;
	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	private static final String FORMAT = "%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c";
	protected double total;
	protected double stop;
	private double step;
	private double currt;
	private String startstr;
	private double startd;
	private float minlen;
	private float maxlen;
	private long time;
	private int mode;
	protected char v;
	private boolean isyn;
	private Boolean closed;
	private IProgress cpro;

	public ConsoleProgressBar() {
		this(100.0D);
	}

	public ConsoleProgressBar(double total) {
		this(total, 0, false);
	}

	public ConsoleProgressBar(double total, int mode, boolean isyn) {
		this(total, mode, '!', isyn);
	}

	public ConsoleProgressBar(String start, String stop, int mode, boolean isyn) {
		this.maxlen = 100.0F;
		this.minlen = 0.0F;
		this.total = ((this.stop = e(stop, 94, 33)) - e(start, 94, 33));
		if (this.total == 0.0D) {
			this.total = ((this.stop = f(stop, 94, 33)) - f(start, 94, 33));
		}
		this.startstr = start;
		this.step = (this.maxlen / this.total);
		this.mode = mode;
		this.v = '!';
		this.isyn = isyn;
		init(mode);
	}

	private ConsoleProgressBar(double total, int mode, char v, boolean isyn) {
		this.maxlen = 100.0F;
		this.minlen = 0.0F;
		this.total = total;
		this.step = (this.maxlen / total);
		this.mode = mode;
		this.v = v;
		this.isyn = isyn;
		init();
	}

	void init(int mode) {
		Character[] characters = new Character[(int) this.maxlen];
		for (int i = 0; i < this.maxlen; i++)
			characters[i] = Character.valueOf('-');
		out.print(String.format(
				"[ " + "%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c"
						+ " ]",
				characters));
		out.print(String.format(" %1$.2f%%", new Object[] { Float.valueOf(0.0F) }));
		this.time = System.currentTimeMillis();
		this.closed = Boolean.valueOf(false);
		this.currt = 0.0D;
		switch (mode) {
		case 0:
			this.cpro = new IProgress() {
				public <X> void resfresh(X... xs) {
					if ((xs[0] instanceof Double)) {
						ConsoleProgressBar.this.show(((Double) xs[0]).doubleValue(), ConsoleProgressBar.this.v);
					} else if ((xs[0] instanceof Long))
						ConsoleProgressBar.this.show(
								ConsoleProgressBar.this.b(((Long) xs[0]).longValue(), ConsoleProgressBar.this.total),
								ConsoleProgressBar.this.v);
				}
			};
			break;
		case 1:
			this.cpro = new IProgress() {
				public <X> void resfresh(X... xs) {
					if ((xs[0] instanceof String)) {
						double e = ConsoleProgressBar.this.stop - ConsoleProgressBar.e(String.valueOf(xs[0]), 94, 33);
						if (e == 0.0D) {
							e = ConsoleProgressBar.this.stop - ConsoleProgressBar.f(String.valueOf(xs[0]), 94, 33);
						}
						ConsoleProgressBar.this.show(ConsoleProgressBar.this.b(ConsoleProgressBar.this.total - e,
								ConsoleProgressBar.this.total), ConsoleProgressBar.this.v);
					}
				}
			};
			break;
		}

	}

	void init() {
		init(this.mode);
	}

	public static String g(String e, int o, int d, long v) {
		int clen = e.length();
		int elen = clen - 1;
		StringBuilder b = new StringBuilder(e);
		do {
			int c = b.charAt(elen) - d;
			long a = c + v;
			v = a / o;
			b.setCharAt(elen, (char) (int) (a % o + d));
			elen--;
		} while (

		elen > 0);
		return b.toString();
	}

	double b(double b, double d) {
		return (b <= 0.0D) && (this.currt > 0.0D) ? d : b;
	}

	static String c(long elapsed) {
		long day = elapsed / 86400000L;
		elapsed %= 86400000L;
		long hour = elapsed / 3600000L;
		elapsed %= 3600000L;
		long minute = elapsed / 60000L;
		elapsed %= 60000L;
		long second = elapsed / 1000L;
		long milli = elapsed % 1000L;
		return String.format("%d天-%02d:%02d:%02d %03d", new Object[] { Long.valueOf(day), Long.valueOf(hour),
				Long.valueOf(minute), Long.valueOf(second), Long.valueOf(milli) });
	}

	public static double e(String e, int max, int s) {
		int elen = e.length() - 1;
		double x = 0.0D;
		for (int i = 0; i < elen; i++)
			x = (x + e.charAt(i) - s) * max - max;
		return x + e.charAt(elen) - s;
	}

	public static long f(String e, int max, int s) {
		int elen = e.length() - 1;
		long x = 0L;
		for (int i = 0; i < elen; i++)
			x = (x + e.charAt(i) - s) * max - max;
		return x + e.charAt(elen) - s;
	}

	void reset() {
		out.print('\r');
	}

	void show(double current, char v) {
		if (current >= this.minlen) {
			current *= this.step;
			long num = Math.round(current);
			StringBuilder builder = new StringBuilder("[ ").append(
					"%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c");
			Character[] chars = new Character[(int) this.maxlen];
			for (int i = 0; i < this.maxlen; i++) {
				chars[i] = Character.valueOf(i < num ? v : '-');
			}
			builder.append(" ]");
			out.print(String.format(builder.toString(), chars));
			Object[] tmp125_122 = new Object[1];
			double tmp129_128 = current;
			this.currt = tmp129_128;
			tmp125_122[0] = Double.valueOf(tmp129_128);
			out.print(String.format(" %1$.2f%%", tmp125_122));
		}
	}

	void showTime(long current) {
		long di = current - this.time;

		out.printf("\t已用: %1$s 预计完成时间: %2$s", new Object[] { c(di),
				DATEFORMAT.format(new Date(Math.round(current + (100.0D - this.currt) / this.currt * di))) });
	}

	public void close() {
		if (this.closed.booleanValue()) {
			synchronized (this.closed) {
				this.closed = Boolean.valueOf(false);
			}
			long current = System.currentTimeMillis();
			long di = current - this.time;
			out.printf("\r\n开始时间: %s 总时: %1.3f-s 完成时间: %s 完成度: %4$.2f%%\r\n",
					new Object[] { DATEFORMAT.format(new Date(this.time)), Float.valueOf((float) di / 1000.0F),
							DATEFORMAT.format(new Date(Math.round(current + (100.0D - this.currt) / this.currt * di))),
							Double.valueOf(this.currt) });
		}
	}

	public void start() {
		if (!this.closed.booleanValue()) {
			this.closed = Boolean.valueOf(true);
			if (this.isyn) {
				switch (this.mode) {
				case 0:
					new Thread(new Runnable() {
						public void run() {
							try {
								while (ConsoleProgressBar.this.closed.booleanValue()) {
									ConsoleProgressBar.this.reset();
									ConsoleProgressBar.this.cpro
											.resfresh(new Double[] { Double.valueOf(ConsoleProgressBar.this.startd) });
									ConsoleProgressBar.this.showTime(System.currentTimeMillis());
									Thread.sleep(1000L);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
					break;

				case 1:
					new Thread(new Runnable() {
						public void run() {
							try {
								while (ConsoleProgressBar.this.closed.booleanValue()) {
									ConsoleProgressBar.this.reset();
									ConsoleProgressBar.this.cpro
											.resfresh(new String[] { ConsoleProgressBar.this.startstr });
									ConsoleProgressBar.this.showTime(System.currentTimeMillis());
									Thread.sleep(1000L);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
					break;
				}

			}
		}
	}

	public void resfresh(Object... currents) {
		if (this.isyn) {
			switch (this.mode) {
			case 0:
				if ((currents[0] instanceof Double)) {
					this.startd = ((Double) currents[0]).doubleValue();
				} else if ((currents[0] instanceof Long))
					this.startd = ((Long) currents[0]).longValue();
				return;
			case 1:
				this.startstr = currents[0].toString();
				return;
			}
			return;
		}

		reset();
		this.cpro.resfresh(currents);
		showTime(System.currentTimeMillis());
	}

	public static void main(String[] args) throws InterruptedException {
		ConsoleProgressBar consoleProgressBar1 = new ConsoleProgressBar(2557607647.0D, 0, false);
		consoleProgressBar1.start();
		for (long i = 0L; i < 2557607647L; i += 666666L) {
			consoleProgressBar1.resfresh(new Object[] { Double.valueOf(i + 0.0D) });
		}

		consoleProgressBar1.close();
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
