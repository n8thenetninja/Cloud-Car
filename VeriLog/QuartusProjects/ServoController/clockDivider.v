module clkDivide(
input wire clk;
input wire reset;
output reg newClk; )
reg q;
reg [7:0] count;

always@(posedge reset or negedge clk) begin
    if (reset) begin
      q <= 1'b0;
    end else if (count = 8'b11111111) begin
      q = ~q;
    end
  end

  always@ (posedge clk)
  begin
  if (reset)
  begin
  count = 8'b00000000
  end
  else
  begin
  count <= count + 1'b1;
  end
  end
  
  
endmodule

