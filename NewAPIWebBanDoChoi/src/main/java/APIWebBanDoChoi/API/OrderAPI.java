package APIWebBanDoChoi.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import APIWebBanDoChoi.API.output.Convert;
import APIWebBanDoChoi.API.output.OrderOutput;
import APIWebBanDoChoi.NewEntity.DDHk;
import APIWebBanDoChoi.service.impl.OrderService;

@RestController
public class OrderAPI {
	@Autowired
	OrderService orderService;
	@Autowired
	Convert convert;

	@GetMapping("/order")
	public OrderOutput getOrderByID(@RequestParam(value="MSDDH") Integer MSDDH )
	{
		return convert.convertToOrderOut(orderService.getOrderByID(MSDDH));
	}
	@GetMapping("/getAllOrder")
	public List<OrderOutput> getAllOrder()
	{
		return convert.convertToListOrderOut(orderService.getAllOrder());
	}
	@GetMapping("/getHistoryOrder")
	public List<OrderOutput> getHistoryOrder(@RequestParam(value="maKH") Integer maKH )
	{
		return convert.convertToListOrderOut(orderService.getHistoryOrder(maKH));
	}
	@PostMapping("/insertOrder")
	public OrderOutput insertOrder(@RequestBody OrderOutput od)
	{
		orderService.insertOrder(convert.convertToDDH(od));
		return od;
	}
	@PutMapping("/cancelOrder")
	public OrderOutput cancelOrder(@RequestBody OrderOutput od)
	{
		orderService.cancelOrder(convert.convertToDDH(od));
		return od;
	}
}
