/*******************************************************************************
*  描述:   跑马灯程序                                                          *
*  连接方法： JP11（P2）和JP1(LED灯) 用8PIN排线连接起来						   *
*																			   *
*******************************************************************************/

#include<reg51.h>
#include <intrins.h>

/*****************************************************************************
*  延时子程序															     *
*																			 *
******************************************************************************/
void delayms(unsigned char ms)
{
	unsigned char i;
	while(ms--)
	{
	for(i = 0; i < 120; i++);
	}
}

/*****************************************************************************
*  主程序															         *
*																			 *
******************************************************************************/
main()
{
	unsigned char LED;
	LED = 0xfe;
	P2 = LED;
	while(1)
	{
	delayms(250);
	LED = _crol_(LED,1);		//循环右移1位，点亮下一个LED  此函数位库函数
	P2 = LED;
	}
}
