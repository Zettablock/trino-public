/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.zettablock.udf;

import org.testng.annotations.Test;

import static io.trino.testing.assertions.Assert.assertEquals;

public class TestUniswapV3ComputePoolAddressFunction {
    private static final String factoryAddr = "0x1f98431c8ad98523631ae4a59f267346ea31f984";

    @Test
    public void testNormalEncoding() {
        // HITS-WETH pool
        String poolAddress = UniswapV3ComputePoolAddressFunction.uniswapV3ComputePoolAddressImpl(
                factoryAddr,
                "0x526a9dd8c610aad36b335094da16df31584c0469",
                "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2",
                10000L);
        assertEquals("0xfef5db12b969b3c94d47e5df600b482cdad8dbc5", poolAddress);

        // CEL-USDT pool
        poolAddress = UniswapV3ComputePoolAddressFunction.uniswapV3ComputePoolAddressImpl(
                factoryAddr,
                "0xaaaebe6fe48e54f431b0c390cfaf0b017d09d42d",
                "0xdac17f958d2ee523a2206206994597c13d831ec7",
                10000L);
        assertEquals("0x8254fdec9b3776a3adaec877b64446b9d862491b", poolAddress);

        // ICSA-HEX pool
        poolAddress = UniswapV3ComputePoolAddressFunction.uniswapV3ComputePoolAddressImpl(
                factoryAddr,
                "0x2b591e99afE9f32eAA6214f7B7629768c40Eeb39",
                "0xfc4913214444aF5c715cc9F7b52655e788A569ed",
                3000L);
        assertEquals("0xff6279ab4d36bc84018fac674a207ad4f90e0fa5", poolAddress);
    }
}