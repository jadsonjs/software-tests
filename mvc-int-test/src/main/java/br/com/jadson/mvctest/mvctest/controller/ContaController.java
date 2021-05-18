/**
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */
package br.com.jadson.mvctest.mvctest.controller;

import br.com.jadson.mvctest.mvctest.exception.NegocioException;
import br.com.jadson.mvctest.mvctest.model.Conta;
import br.com.jadson.mvctest.mvctest.repository.ContaRepository;
import br.com.jadson.mvctest.mvctest.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * ContaController.java
 *
 */
@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService service;

	@Autowired
	private ContaRepository repository;

	@GetMapping("/")
	public ModelAndView getContas(ModelAndView mav){
		mav.addObject("contas",repository.findAll());
		return mav;
	}

	@GetMapping("/conta/{id}")
	public ModelAndView getConta(@PathVariable("id") Long id,ModelAndView mav){
		mav.setViewName("conta/conta");
		mav.addObject("conta",repository.findById(id).get());
		return mav;
	}

	/**
	 * Return to the create page to create a new object
	 * @param mav
	 * @return
	 */
	@GetMapping("/precreate")
	public ModelAndView preCreate(ModelAndView mav){
		mav.addObject("conta",new Conta());
		return mav;
	}



	@PostMapping("/abrir")
	public ModelAndView abrirConta(@Valid Conta conta, BindingResult bindingResult, ModelAndView mav, HttpServletRequest req) {
		if (!bindingResult.hasErrors()) {
			service.abrirConta(conta);
			mav.addObject("successMessage", "Conta aberta com sucesso.");
		}
		return mav;
	}

	@ExceptionHandler(NegocioException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}



}


