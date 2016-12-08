/*
Clock divider to convert 50MHz to ~200kHz

Project: Cloud Car
Author:  Nathan Larson
Date:	   10/22/2016
*/

module clockDivider(
input wire clk,
input wire reset,
output reg newClk );
reg [10:0] count;

always@(negedge reset, negedge clk) begin
    if (!reset) begin
      newClk <= 1'b0;
    end else if (count == 8'b01111111111) begin
      newClk = ~newClk;
    end
  end

  always@ (posedge clk, negedge reset)
  begin
  if (!reset)
  begin
  count <= 1'b0;
  end
  else
  begin
  count <= count + 1'b1;
  end
  end
  
  
endmodule

