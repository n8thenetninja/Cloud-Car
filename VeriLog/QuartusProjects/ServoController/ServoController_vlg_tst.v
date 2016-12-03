/*
Servo Controller test bench

Author: 	Nathan Larson
Date: 	10/20/2016
*/

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

