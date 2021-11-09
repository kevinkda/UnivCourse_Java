﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MessageTransmitter_ServerSide
{
    public partial class FormServerSide : Form
    {
        public FormServerSide()
        {
            InitializeComponent();
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonStart_Click(object sender, EventArgs e)
        {
            try
            {
                //负责监听的Scoket
                Socket socketListening = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                //服务端IP地址
                IPAddress IPAddress = IPAddress.Any;
                //端口号
                IPEndPoint Port = new IPEndPoint(IPAddress, Convert.ToInt32(textBoxPort.Text));
                //绑定端口号
                socketListening.Bind(Port);
                ShowMsg("监听成功\tPort : " + Convert.ToString(Port.Port));
                socketListening.Listen(Convert.ToInt32(textBoxMaxNumConnect.Text));
                //创建监听线程
                Thread th = new Thread(Listen);
                th.IsBackground = true;
                th.Start(socketListening);
            }
            catch
            {
 
            }
        }

        /// <summary>
        /// 接收数据
        /// </summary>
        /// <param name="obj"></param>
        void Receive(object obj)
        {
            Socket socketSend = obj as Socket;
            while (true)
            {
                try
                {
                    byte[] buffer = new byte[1024 * 1024 * 2];
                    int r = socketSend.Receive(buffer);
                    // Thread.Sleep(200);
                    if (r == 0)
                    {
                        break;
                    }
                    string str = Encoding.UTF8.GetString(buffer, 0, r);
                    //str = socketSend.RemoteEndPoint + str;
                    ShowMsg(socketSend.RemoteEndPoint + " : " + str);
                }
                catch { }
            }
        }

        Dictionary<string, Socket> dic = new Dictionary<string, Socket>();

        /// <summary>
        /// 创建监听
        /// </summary>
        /// <param name="obj"></param>
        public void Listen(object obj)
        {
            Socket socketWacth = obj as Socket;
            try
            {
                //创建等待通信的Socket
                Socket socketSend = socketWacth.Accept();
                //192.168.1.4 : 连接成功
                ShowMsg(socketSend.RemoteEndPoint + " : 连接成功");
                dic.Add(socketSend.RemoteEndPoint.ToString(), socketSend);
                comboBoxIPPort.Items.Add(socketSend.RemoteEndPoint.ToString());
                comboBoxIPPort.Text = socketSend.RemoteEndPoint.ToString();
                Thread th = new Thread(Receive);
                th.IsBackground = true;
                th.Start(socketSend);
            }
            catch
            {

            }
        }
        // Dictionary<string, Socket> dic = new Dictionary<string, Socket>();

        /// <summary>
        ///
        /// </summary>
        /// <param name="str"></param>
        public void ShowMsg(string str)
        {
            textBoxMessage.AppendText(str + "\r\n");
        }

        private void buttonStop_Click(object sender, EventArgs e)
        {

        }

        private void FormServerSide_Load(object sender, EventArgs e)
        {
            Control.CheckForIllegalCrossThreadCalls = false;
            buttonStart_Click(sender, e);
        }

        private void buttonExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void toolStripMenuItemStart_Click(object sender, EventArgs e)
        {
            buttonStart_Click(sender, e);
        }

        private void ToolStripMenuItemStop_Click(object sender, EventArgs e)
        {
            buttonStop_Click(sender, e);
        }

        private void ToolStripMenuItemExit_Click(object sender, EventArgs e)
        {
            buttonExit_Click(sender, e);
        }

        private void ToolStripMenuItemSend_Click(object sender, EventArgs e)
        {
            buttonSendMessage_Click(sender, e);
        }

        private void buttonSendMessage_Click(object sender, EventArgs e)
        {
            string str = textBoxTransmissibleMessage.Text;
            byte[] buffer = Encoding.UTF8.GetBytes(str);
            // socketSend.Send(buffer);
            dic[comboBoxIPPort.SelectedItem.ToString()].Send(buffer);
        }
    }
}