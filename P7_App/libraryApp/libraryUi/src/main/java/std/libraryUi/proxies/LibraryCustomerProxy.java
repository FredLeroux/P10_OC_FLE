package std.libraryUi.proxies;

import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import std.libraryUi.beans.LibraryCustomerLogBean;

@FeignClient(name = "gateway-zuul" )
@RibbonClient(name = "libraryCustomers")
public interface LibraryCustomerProxy {

	@GetMapping(value = "/libraryCustomers/getCustomerLog")//libraryCustomers
	public Optional<LibraryCustomerLogBean> getCustomer(@RequestParam(value = "username") String customerEmail);

	@GetMapping("/libraryCustomers/getCustomer")
	public String getCustomerUserName(@RequestParam(value = "token") String token);

}
