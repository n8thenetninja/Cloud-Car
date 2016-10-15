// Copyright (C) 1991-2014 Altera Corporation. All rights reserved.
// Your use of Altera Corporation's design tools, logic functions 
// and other software and tools, and its AMPP partner logic 
// functions, and any output files from any of the foregoing 
// (including device programming or simulation files), and any 
// associated documentation or information are expressly subject 
// to the terms and conditions of the Altera Program License 
// Subscription Agreement, the Altera Quartus II License Agreement,
// the Altera MegaCore Function License Agreement, or other 
// applicable license agreement, including, without limitation, 
// that your use is for the sole purpose of programming logic 
// devices manufactured by Altera and sold by Altera or its 
// authorized distributors.  Please refer to the applicable 
// agreement for further details.

// *****************************************************************************
// This file contains a Verilog test bench template that is freely editable to  
// suit user's needs .Comments are provided in each section to help the user    
// fill out necessary details.                                                  
// *****************************************************************************
// Generated on "10/14/2016 15:56:44"
                                                                                
// Verilog Test Bench template for design : ServoController
// 
// Simulation tool : ModelSim-Altera (Verilog)
// 

`timescale 1 ns/ 1 ps
module ServoController_vlg_tst();

// test vector input registers
reg clk;
reg enable;
reg [7:0] pulse_in;
// wires                                               
wire PWM_out;

pwm_generator #(.PWM_DEPTH(8)) i1			// pwm0 is your instance name
	(//ports 
		.pwm( PWM_out ),					// PWM output signal
		.duty_cycle( pulse_in ),	// PWM duty cycle (vector)
		.rst_n( enable ),			// Active-low reset
		.clk( clk )				// Clock signal
		);

initial                                                
begin                                                  
// code that executes only once                        
// insert code here --> begin    
$display("Running testbench");                      
enable = 0;
clk = 0;
pulse_in = 8'b00001111;
#10
enable = 1;
#20000;
pulse_in = 8'b00111111;
#20000;
pulse_in = 8'b11111000;
#20000;

// --> end                                             
                       
end                                                    
always #10 clk = ~clk;                                              
// optional sensitivity list                           
// @(event1 or event2 or .... eventn)                                                                    
endmodule

